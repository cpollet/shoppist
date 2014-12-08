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

package net.cpollet.shoppist.web.controller;

import net.cpollet.shoppist.da.data.Shop;
import net.cpollet.shoppist.da.repository.ShopRepository;
import net.cpollet.shoppist.web.rest.ListWrapper;
import net.cpollet.shoppist.web.rest.ListWrapperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Christophe Pollet
 */
@RestController
public class ShopsController {
	@Autowired
	private ShopRepository shopRepository;

	@RequestMapping("/api/v1/shops")
	public ListWrapper<Shop> getLists() {
		return ListWrapperBuilder.<Shop>aListWrapper() //
				.withList(shopRepository.findAll()) //
				.build();
	}
}
