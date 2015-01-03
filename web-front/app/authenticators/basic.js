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

import Base from 'simple-auth/authenticators/base';
import Ember from 'ember';

var BasicAuthenticator = Base.extend({
	restore: function (data) {
		Ember.Logger.debug('restore: ', data);
		return new Ember.RSVP.Promise(function (resolve, reject) {
				Ember.$.ajax('/api/v1/token/' + data.object, {
					method: 'PUT'
				}).then(
					function (response) {
						Ember.Logger.debug('restore: success', response);
						Ember.Logger.debug('restore token: ', response.object);
						Ember.$.ajaxSetup({
							headers: {
								'X-Authorization': response.object
							}
						});
						resolve(response);
					},
					function (/*error*/) {
						Ember.Logger.debug('restore: failed');
						reject();
					}
				);
			});
	},
	authenticate: function (options) {
		Ember.Logger.debug('authenticate as ' + options.username + ":" + options.password);

		return new Ember.RSVP.Promise(function (resolve, reject) {
			Ember.$.post('/api/v1/token', {
				'username': options.username,
				'password': options.password
			}).then(
				function (response) {
					Ember.Logger.debug('success: ', response);
					Ember.Logger.debug('token:', response.object);
					Ember.$.ajaxSetup({
						headers: {
							'X-Authorization': response.object
						}
					});
					resolve(response);
				},
				function (error) {
					Ember.Logger.debug('error:', error);
					reject();
				}
			);
		});
	},
	invalidate: function (/*data*/) {
		console.log('invalidate session');
		return new Ember.RSVP.Promise(function (resolve/*, reject*/) {
			var token = Ember.$.ajaxSettings.headers['X-Authorization'];

			Ember.$.ajax('/api/v1/token/' + token, {
				method: 'DELETE',
				complete: function () {
					Ember.Logger.debug('invalidated server-side');
					resolve();
				}
			});
		});
	}
});

export default BasicAuthenticator;