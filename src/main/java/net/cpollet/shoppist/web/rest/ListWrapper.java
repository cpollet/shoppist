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

import java.util.List;
import java.util.Map;

/**
 * @author Christophe Pollet
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListWrapper<T> {
	private List<T> items;

	private String nextPage;
	private String previousPage;
	private Integer pageSize;
	private Long totalItemsCount;
	private Integer totalPagesCount;
	private Map<String, Link> links;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalItemsCount() {
		return totalItemsCount;
	}

	public void setTotalItemsCount(Long totalItemsCount) {
		this.totalItemsCount = totalItemsCount;
	}

	public void setTotalPagesCount(Integer totalPagesCount) {
		this.totalPagesCount = totalPagesCount;
	}

	public Integer getTotalPagesCount() {
		return totalPagesCount;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	public void setLinks(Map<String, Link> links) {
		this.links = links;
	}
}
