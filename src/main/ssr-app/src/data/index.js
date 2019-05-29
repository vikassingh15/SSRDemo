/* @flow */
import { combineReducers } from 'redux';
import type { Dispatch } from 'redux';

import comments from './modules/comments';

export default combineReducers({
  comments
});

export type Thunk<A> = (dispatch: Dispatch<A>, getState: () => Object) => any;
