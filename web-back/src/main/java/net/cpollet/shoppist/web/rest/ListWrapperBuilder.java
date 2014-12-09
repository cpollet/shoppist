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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class ListWrapperBuilder<T> {
	private List<T> objects;
	private Integer pageSize;
	private Long totalItemsCount;
	private Integer totalPagesCount;
	private Map<String, Link> links;

	private ListWrapperBuilder() {
		withList(Collections.<T>emptyList());
	}

	public static <T> ListWrapperBuilder<T> aListWrapper() {
		return new ListWrapperBuilder<>();
	}

	public ListWrapperBuilder<T> withList(List<T> objects) {
		this.objects = objects;
		return this;
	}

	public ListWrapperBuilder<T> withTotalItemsCount(Long totalItemsCount) {
		this.totalItemsCount = totalItemsCount;
		return this;
	}

	public ListWrapperBuilder<T> withTotalPagesCount(Integer totalPagesCount) {
		this.totalPagesCount = totalPagesCount;
		return this;
	}

	public ListWrapperBuilder<T> withPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public ListWrapperBuilder<T> withPreviousPageUrl(String previousPageUrl) {
		if (links == null) {
			links = new HashMap<>();
		}
		links.put(Link.Relation.PREVIOUS_PAGE.getDescription(), new Link(previousPageUrl, Link.Method.GET));
		return this;
	}

	public ListWrapperBuilder<T> withNextPageUrl(String nextPageUrl) {
		if (links == null) {
			links = new HashMap<>();
		}
		links.put(Link.Relation.NEXT_PAGE.getDescription(), new Link(nextPageUrl, Link.Method.GET));
		return this;
	}

	public ListWrapper<T> build() {
		ListWrapper<T> listWrapper = new ListWrapper<>();
		listWrapper.setItems(objects);
		listWrapper.setTotalItemsCount(totalItemsCount);
		listWrapper.setTotalPagesCount(totalPagesCount);
		listWrapper.setPageSize(pageSize);
		listWrapper.setLinks(links);

		return listWrapper;
	}
}
