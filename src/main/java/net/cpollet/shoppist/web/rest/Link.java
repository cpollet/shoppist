/*
 * Copyright 2014 Christophe Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.cpollet.shoppist.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Christophe Pollet
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link {
	public enum Relation {
		PREVIOUS_PAGE("prevPage"), //
		NEXT_PAGE("nextPage"), //
		SELF("self"), //
		INSERT("insert"), //
		NEXT("next"), //
		PREV("prev");

		private final String description;

		private Relation(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

	public enum Method {
		GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE;
	}

	private String href;
	private String method;

	public Link(String href, Method method) {
		this.href = href;
		this.method = method.name();
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
