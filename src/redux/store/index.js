'use strict';

import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import reducers from '../reducers/index';

const applyStoreMiddleware = applyMiddleware(thunk)(createStore);
export const store = applyStoreMiddleware(reducers);