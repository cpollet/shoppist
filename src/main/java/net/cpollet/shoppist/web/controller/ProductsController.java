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

import net.cpollet.shoppist.da.data.Product;
import net.cpollet.shoppist.da.repository.ProductRepository;
import net.cpollet.shoppist.web.rest.ListWrapper;
import net.cpollet.shoppist.web.rest.ListWrapperBuilder;
import net.cpollet.shoppist.web.rest.RestResponse;
import net.cpollet.shoppist.web.rest.RestResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;


/**
 * @author Christophe Pollet
 */
@Controller
public class ProductsController {
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/api/v1/products", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public RestResponse createProduct(@RequestBody @Valid Product product) {
		product.setId(null);
		productRepository.save(product);

		return RestResponseBuilder.aRestResponse() //
				.withObject(product) //
				.build();
	}

	@RequestMapping(value = "/api/v1/products", method = RequestMethod.GET)
	@ResponseBody
	public ListWrapper<Product> getProducts() {
		Page<Product> page = productRepository.findAll(new PageRequest(0, 20));

		ListWrapperBuilder<Product> listWrapper = ListWrapperBuilder.<Product>aListWrapper() //
				.withList(page.getContent()) //
				.withPageSize(page.getSize())
				.withTotalPagesCount(page.getTotalPages()) //
				.withTotalItemsCount(page.getTotalElements());

		return listWrapper.build();
	}
}
