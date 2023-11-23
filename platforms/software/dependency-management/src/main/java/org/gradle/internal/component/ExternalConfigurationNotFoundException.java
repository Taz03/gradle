/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.component;

/**
 * This exception is used to indicate that a named configuration for an <strong>Ivy</strong> or <strong>Maven</strong>
 * dependency wasn't found when resolving a Graph.
 */
public class ExternalConfigurationNotFoundException extends ConfigurationNotFoundException {
    public ExternalConfigurationNotFoundException(String message) {
        super(message);
    }
}
