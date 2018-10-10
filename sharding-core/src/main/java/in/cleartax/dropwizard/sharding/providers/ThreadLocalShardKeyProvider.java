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

package in.cleartax.dropwizard.sharding.providers;

import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

/**
 * Created on 23/09/18
 */
@NoArgsConstructor
public class ThreadLocalShardKeyProvider implements ShardKeyProvider {

    private static ThreadLocal<String> context = new ThreadLocal<>();

    @Override
    @Nullable
    public String getKey() {
        return context.get();
    }

    @Override
    public void setKey(String shardId) {
        context.set(shardId);
    }
}