/* @flow */
import React from 'react';
import { Route, Switch } from 'react-router';
import AppMeta from './AppMeta';

import { AddComment, CommentList, Errors, Navigation } from '../components';

const App = () => (
  <div>
    <AppMeta />
    <Navigation />

    <div className="container">
      <Switch>
        <Route exact path="/" component={CommentList} />
        <Route exact path="/add" component={AddComment} />
        <Route component={Errors} />
      </Switch>
    </div>
  </div>
);

export default App;
