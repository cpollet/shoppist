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

package net.cpollet.shoppist.service;

import net.cpollet.shoppist.da.data.ShoppingList;
import net.cpollet.shoppist.da.repository.ShoppingListRepository;
import net.cpollet.shoppist.web.controller.ListNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Christophe Pollet
 */
public class DefaultShoppingListService implements ShoppingListService {
	@Autowired
	private ShoppingListRepository shoppingListRepository;

	@Override
	public ShoppingList find(String id) {
		try {
			ShoppingList shoppingList = shoppingListRepository.findOne(new ObjectId(id));

			if (shoppingList == null) {
				throw new ListNotFoundException("List [" + id + "] does not exist");
			}

			return shoppingList;
		}
		catch (IllegalArgumentException e) {
			throw new ListNotFoundException("List [" + id + "] does not exist");
		}
	}
}
