import React,{Component} from 'react';
import {Button,Modal,Form,Select,Label,Message} from 'semantic-ui-react';
import sha256 from 'crypto-js/sha256';
import Bus from '../MessageBus/Bus';
import axios from 'axios';
class SignUpModal extends Component{
    constructor(props){
        super(props);
        this.state = this.props;
    }
    componentDidMount(){
        Bus.subscribe(()=>{
            let state = Bus.getState();
            if(state.type == "signUpModal") {
                if(state.data == 'open'){
                    this.setState({open:true})
                }
            }
        })
    }
    signUp(){
        const errors = [];
        var success = true;
        if(this.state.password != this.state.confirmPassword){
            errors.push("Your comfirmed password is not the same as your password");
            this.setState({error:{confirmPassword:true}});
            success = false;
        }
        this.setState({errors:errors});
        if(success){
            axios.post("request/login/signUp",{
                name:this.state.userID,
                password:sha256(this.state.password).toString(),
                email:this.state.email,
                phone:this.state.phone
            }).then((res)=>{
                alert(res.data.content);
                this.setState({open:false});
                window.location.href = window.location.protocol + '//' + window.location.host + "/WebApplication";
            });
        }
    }
    render(){
        return (
            <Modal open={this.state.open} centered={true}>
                <Modal.Header>
                    Sign Up
                </Modal.Header>
                <Modal.Content >
                    <Form>
                        <Form.Field>
                            <Label>User ID:</Label>
                            <Form.Input required={true} onChange={(arg1,arg2)=>{this.setState({userID:arg2.value})}}/>
                        </Form.Field>
                        <Form.Field>
                            <Label>Password:</Label>
                            <Form.Input required={true} type="password" onChange={(arg1,arg2)=>{this.setState({password:arg2.value})}}/>
                        </Form.Field>
                        <Form.Field>
                            <Label>Confirm Password:</Label>
                            <Form.Input error={this.state.error.confirmPassword} required={true} type="password" onChange={(arg1,arg2)=>{this.setState({confirmPassword:arg2.value})}}/>
                        </Form.Field>
                        <Form.Field>
                            <Label>Email:</Label>
                            <Form.Input required={true} onChange={(arg1,arg2)=>{this.setState({email:arg2.value})}}/>
                        </Form.Field>
                        <Form.Field>
                            <Label>Phone:</Label>
                            <Form.Input required={true} onChange={(arg1,arg2)=>{this.setState({phone:arg2.value})}}/>
                        </Form.Field>
                            <Message visible={!!this.state.errors.length} warning
                                     header='Please correct the following issues: '
                                     list={this.state.errors}/>
                        </Form>
                </Modal.Content>
                <Modal.Actions>
                    <Button primary onClick={()=>{this.signUp()}}>Sign Up</Button>
                    <Button onClick = {()=>{this.setState({open:false})}}>Close</Button>
                </Modal.Actions>
            </Modal>
        );
    }
}
SignUpModal.defaultProps = {
    open:false,
    error:{},
    errors:[]
};
export default SignUpModal;