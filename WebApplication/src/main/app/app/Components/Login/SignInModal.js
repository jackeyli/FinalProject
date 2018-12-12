import React,{Component} from 'react';
import {Button,Modal,Form,Select,Label} from 'semantic-ui-react';
import sha256 from 'crypto-js/sha256';
import axios from 'axios';
import Bus from '../MessageBus/Bus';
class SignInModal extends Component{
    constructor(props){
        super(props);
        this.state = this.props;
    }
    componentDidMount(){
        Bus.subscribe(()=>{
            let state = Bus.getState();
            if(state.type == "signInModal") {
                 if(state.data == 'open'){
                     this.setState({open:true})
                 }
            }
        })
    }
    signIn(){
        axios.post("request/login/",{
            name:this.state.userID,
            password:sha256(this.state.password).toString()
        }).then((res)=>{
            alert(res.data.content);
            this.setState({open:false});
            window.location.href = window.location.protocol + '//' + window.location.host + '/WebApplication';
        });
    }
    render(){
        return (
            <Modal  open={this.state.open} centered={true}>
            <Modal.Header>
                Sign In
            </Modal.Header>
            <Modal.Content>
        <Form>
        <Form.Field>
        <Label>User ID:</Label>
        <Form.Input required={true} onChange={(arg1,arg2)=>{this.setState({userID:arg2.value})}}/>
        </Form.Field>
        <Form.Field>
        <Label>Password:</Label>
        <Form.Input required={true} type="password" onChange={(arg1,arg2)=>{this.setState({password:arg2.value})}}/>
        </Form.Field>
        </Form>
        </Modal.Content>
        <Modal.Actions>
        <Button primary onClick={()=>{this.signIn()}}>Sign In</Button>
        <Button onClick = {()=>{this.setState({open:false})}}>Close</Button>
        </Modal.Actions>
        </Modal>
    );
    }
}
SignInModal.defaultProps = {
    open:false
};
export default SignInModal;