/*
 * Copyright 2015 Christophe Pollet
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

import Ember from 'ember';

var AuthenticationInitializer = {
	name: 'authentication',
	after: 'simple-auth',
	initialize: function (container/*, application*/) {
		var applicationRoute = container.lookup('route:application');
		var session = container.lookup('simple-auth-session:main');

		session.on('sessionAuthenticationSucceeded', function () {
			Ember.Logger.debug('login:success');
			applicationRoute.transitionTo('lists');
		});

		session.on('sessionAuthenticationFailed', function () {
			Ember.Logger.debug('login:failure');
		});

		session.on('sessionInvalidationSucceeded', function () {
			Ember.Logger.debug('logout:success');
			applicationRoute.transitionTo('index');
		});

		session.on('sessionInvalidationFailed', function () {
			Ember.Logger.debug('logout:failure');
		});
	}
};

export default AuthenticationInitializer;