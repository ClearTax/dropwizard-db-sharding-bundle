/*
 * Copyright 2018 Cleartax
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package in.cleartax.dropwizard.sharding.utils.resolvers.shard;

import in.cleartax.dropwizard.sharding.resolvers.shard.ShardResolver;
import in.cleartax.dropwizard.sharding.transactions.ReuseSession;
import in.cleartax.dropwizard.sharding.transactions.TenantIdentifier;
import in.cleartax.dropwizard.sharding.utils.dao.BucketToShardMappingDAO;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DbBasedShardResolver implements ShardResolver {
    private static ConcurrentMap<String, String> bucketShardCache = new ConcurrentHashMap<>();

    private final BucketToShardMappingDAO dao;

    @Override
    public String resolve(String bucketId) {
        if (bucketShardCache.containsKey(bucketId)) {
            return bucketShardCache.get(bucketId);
        }
        String shardId = getShardId(bucketId);
        bucketShardCache.put(bucketId, shardId);
        return shardId;
    }

    @UnitOfWork
    @TenantIdentifier(useDefault = true)
    @ReuseSession
    public String getShardId(String bucketId) {
        Optional<String> shardId = dao.getShardId(bucketId);
        if (!shardId.isPresent()) {
            throw new IllegalAccessError(String.format("%s bucket not mapped to any shard", bucketId));
        }
        return shardId.get();
    }
}
