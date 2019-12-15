import React from 'react';
import '../../css/App.css';
import User from '../User/User'
import Home from '../Home/Home';
import Register from '../Register/Register';
import {Redirect, Route, Switch} from 'react-router-dom';
import Device from "../Device/Device";


export const App = () => {
    return (
        <div>
            <Switch>
                <Route path="/home" component={Home}/>
                <Route exact path="/">
                    <Redirect to="/home"/>
                </Route>
                <Route path="/register"  component={Register}/>
                <Route path="/user" component={User}/>
                <Route path="/devices" component={Device}/>
            </Switch>
        </div>
    );
};