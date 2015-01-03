import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
	location: config.locationType
});

Router.map(function () {
	this.route('about');
	this.route('contact');
	this.route('login');
	this.route('profile');
	this.resource('shops', {path: '/shops'}, function () {
	});
	this.resource('lists', {path: '/lists'}, function () {
		this.route('new');
	});
});

export default Router;
