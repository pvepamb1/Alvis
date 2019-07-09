import React from 'react';
import '../../css/App.css';
import User from '../User/User'
import Home from '../Home/Home';
import Register from '../Register/Register';
import {Redirect, Route, Switch} from 'react-router-dom';
import Device from "../Device/Device";


export const App = () => {
    let url = "http://192.168.1.200:8080";
    return (
        <div>
            <Switch>
                <Route exact path="C:/Users/Prasenna/WebstormProjects/homunculus-react/build/home" component={Home}/>
                <Route exact path="C:/Users/Prasenna/WebstormProjects/homunculus-react/build/">
                    <Redirect to="C:/Users/Prasenna/WebstormProjects/homunculus-react/build/home"/>
                </Route>
                {/*<Route exact path="/register" render={(props) => <Register url={url} {...props}/>}/>
                <Route exact path="/user" render={(props) => <User url={url} {...props}/>}/>
                <Route exact path="/devices" render={(props) => <Device url={url} {...props}/>}/>*/}
            </Switch>
        </div>
    );
};