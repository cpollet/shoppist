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

import net.cpollet.shoppist.da.data.ShoppingList;
import net.cpollet.shoppist.da.repository.ShoppingListRepository;
import net.cpollet.shoppist.service.ShoppingListService;
import net.cpollet.shoppist.web.rest.ListWrapper;
import net.cpollet.shoppist.web.rest.ListWrapperBuilder;
import net.cpollet.shoppist.web.rest.RestResponse;
import net.cpollet.shoppist.web.rest.RestResponseBuilder;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Christophe Pollet
 */
@RestController
public class ShoppingListsController {
	@Autowired
	private ShoppingListRepository shoppingListRepository;

	@Autowired
	private ShoppingListService shoppingListService;

	@RequestMapping("/api/v1/lists")
	public ListWrapper<ShoppingList> getLists() {
		return ListWrapperBuilder.<ShoppingList>aListWrapper() //
				.withList(shoppingListRepository.findAll()) //
				.withNextPageUrl("http://localhost:8080") //
				.build();
	}

	@RequestMapping(value = "/api/v1/lists", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse createList(@RequestBody String name) {
		ShoppingList shoppingList = new ShoppingList();

		shoppingList.setName(name);

		ShoppingList newShoppingList = shoppingListRepository.save(shoppingList);

		return RestResponseBuilder.aRestResponse() //
				.withObject(newShoppingList) //
				.build();
	}

	@RequestMapping(value = "/api/v1/lists/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public RestResponse updateList(@PathVariable("id") String id, @RequestBody ShoppingList shoppingList) {
		ShoppingList existingShoppingList;

		try {
			existingShoppingList = shoppingListRepository.findOne(new ObjectId(id));
		}
		catch (IllegalArgumentException e) {
			throw new ListNotFoundException("List [" + id + "] does not exist");
		}

		if (existingShoppingList == null) {
			throw new ListNotFoundException("List [" + id + "] does not exist");
		}

		existingShoppingList.setName(shoppingList.getName());
		existingShoppingList.setItems(shoppingList.getItems());

		shoppingListRepository.save(existingShoppingList);

		return RestResponseBuilder.aRestResponse() //
				.withObject(existingShoppingList) //
				.build();
	}

	@ExceptionHandler({ListNotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public RestResponse usernameError(HttpServletRequest req, Exception exception) {
		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.NOT_FOUND.value()) //
				.withErrorStatus("ListNotFound") //
				.withErrorDescription(exception.getMessage()) //
				.build();
	}
}
