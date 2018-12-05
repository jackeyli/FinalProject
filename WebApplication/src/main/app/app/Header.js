import React from "react";
import {Link} from "react-router";
export const Header = (props)=>{
    return (
        <nav class="navbar navbar-default">

        <div>
        <ul class="navbar-nav nav">
        <li>
            <Link to="/overview">Home</Link>
        </li>
        <li >
            <Link to="/pricediff">Price Diff</Link>
        </li>
        <li >
            <Link to="/Subscription">Subscription</Link>
        </li>
        <li >
            <Link to="/profile">Profile</Link>
        </li>
        </ul>
        </div>
        </nav>
    )
}