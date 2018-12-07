import React from "react";
import {Link} from "react-router";
import {Button,Menu} from 'semantic-ui-react';
import Bus from './Components/MessageBus/Bus';

function onOpenSignInModal(){
    Bus.dispatch({
        type:'signInModal',
        data:'open'
    })
}

function onOpenSignUpModal(){
    Bus.dispatch({
        type:'signUpModal',
        data:'open'
    })
}

export const Header = (props)=>{
    return (
        <Menu secondary>
            <Menu.Item name="home">
                <Link to="/overview">Home</Link>
            </Menu.Item>
            <Menu.Item name="pricediff">
                <Link to="/pricediff">Price Diff</Link>
            </Menu.Item>
            <Menu.Item name="subscription">
                <Link to="/subscription">Subscription</Link>
            </Menu.Item>
            <Menu.Item name="notificationHistory">
                <Link to="/notificationHistory">Notification History</Link>
            </Menu.Item>
            <Menu.Menu position='right'>
                <Menu.Item>
                    <Button size="small" onClick={onOpenSignInModal}>Sign In</Button>
                </Menu.Item>
                <Menu.Item>
                    <Button size="small" onClick={onOpenSignUpModal}>Sign Up</Button>
                </Menu.Item>
            </Menu.Menu>
        </Menu>
    )
}