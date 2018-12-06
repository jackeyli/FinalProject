import React,{Component} from 'react';
import SignInModal from './Components/Login/SignInModal';
import SignUpModal from './Components/Login/SignUpModal';
import {Header} from './Header';
export class Home extends Component {
    render(){
        return (<div className="container">
             <div className="row">
                <Header/>
             </div>
             <div className="row">
                {this.props.children}
             </div>
             <SignInModal/>
             <SignUpModal/>
        </div>)
    }
}
