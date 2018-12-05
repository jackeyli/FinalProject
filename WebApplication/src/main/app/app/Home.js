import React,{Component} from 'react';
import {Container} from 'semantic-ui-react';
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
        </div>)
    }
}
