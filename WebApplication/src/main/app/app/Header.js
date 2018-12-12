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
            <Menu.Item as ={Link} to = "/WebApplication/overview" name="home"/>
            <Menu.Item as ={Link} to="/WebApplication/pricediff" name="pricediff"/>
            <Menu.Item as={Link} to="/WebApplication/subscription" name="subscription"/>
            <Menu.Item as={Link} to="/WebApplication/notificationHistory" name="notificationHistory"/>
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