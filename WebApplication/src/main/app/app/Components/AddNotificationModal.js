import React,{Component} from 'react';
import {Button,Modal,Form,Select,Label} from 'semantic-ui-react';
import Bus from './MessageBus/Bus'
import axios from 'axios';
class AddNotificationModal extends Component{
    constructor(props){
        super(props);
        this.state = this.props;
    }
    openModal(){
        this.setState({open:true});
    }
    onChangeCurrency(arg1,arg2){
        this.setState({currency:arg2.value});
    }

    onChangeNotifyType(arg1,arg2){
        this.setState({notifyType:arg2.value});
    }
    onChangeType(arg1,arg2){
        this.setState({type:arg2.value});
    }
    onChangeMarketPlace(arg1,arg2) {
        this.setState({ma_market_place: arg2.value});
    }
    onChangePeriod(arg1,arg2){
        this.setState({interval:arg2.value});
    }
    onChangeDirection(arg1,arg2){
        this.setState({direction:arg2.value});
    }
    onSubmit(){
        const notifyObj = {
            userOid:0,
            type:this.state.type,
            currencyName:this.state.currency,
            notifyType:this.state.notifyType,
            ma_arg_1:this.state.ma_arg_1,
            ma_arg_2:this.state.ma_arg_2,
            ma_market_place:this.state.ma_market_place,
            interval:this.state.interval,
            diffThre:this.state.diffThre,
            direction:this.state.direction
        }
        axios.post('request/test/storeNotiCondition',notifyObj).then((res)=>{
            this.setState({open:false});
            Bus.dispatch({
                type:'subscriptionChange'
            });
        });
    }
    render(){
        const MACondition =(
            <Form>
            <Form.Field>
                <Select placeholder="Choose a direction"
                value={this.state.direction}
                options={
                    [{key:'UP',value:'UP',text:'Up'},{key:'DOWN',value:'DOWN',text:'Down'}]
                }
                onChange = {this.onChangeDirection.bind(this)}
                />
                <Label pointing> Please Choose a direction</Label>
            </Form.Field>
            <Form.Field>
                    <Form.Select
                        required={true}
                        placeholder="Choose a market place"
                        value = {this.state.ma_market_place}
                        options={this.props.markets.map((t)=>{return {key:t,value:t,text:t}})}
                        onChange={this.onChangeMarketPlace.bind(this)}
                    />
                    <Label pointing> Please Choose a market place </Label>
            </Form.Field>
            <Form.Field>
                <Form.Select placeholder="Choose a Period"
                    value={this.state.ma_interval}
                    options = {
                        [
                            {key:'15MIN',value:'15MIN',text:'15MIN'},
                            {key:'30MIN',value:'30MIN',text:'30MIN'},
                            {key:'1HOUR',value:'1HOUR',text:'1HOUR'},
                            {key:'4HOUR',value:'4HOUR',text:'4HOUR'},
                            {key:'DAY',value:'DAY',text:'DAY'}
                        ]
                    }
                    onChange={this.onChangePeriod.bind(this)}
                    />
                    <Label pointing> Please Choose an Interval </Label>
            </Form.Field>
            <Form.Field>
                <Form.Input type="number" required={true} value={this.state.ma_arg_1} onChange={
        (arg1,arg2)=>{this.setState({ma_arg_1:arg2.value})}
        }/>
                <Label pointing> Please input the short period </Label>
            </Form.Field>
            <Form.Field>
                <Form.Input type="number" required={true} value={this.state.ma_arg_2}
                onChange={(arg1,arg2)=>{this.setState({ma_arg_2:arg2.value})}}
                />
                <Label pointing> Please input the long period </Label>
            </Form.Field>
            </Form>
        );
        const open = this.state.open;
        const ThreHoldCondition = (
            <Form>
                <Form.Field>
                    <Form.Input type="number" required = {true} value={this.state.threDiff}
                    onChange={(arg1,arg2)=>{this.setState({threDiff:arg2.value})}}/>
                    <Label pointing> Please input the Threshold</Label>
                </Form.Field>
            </Form>
        )
        return (
            <div>
                <Button style={{'margin-left':'10px'}}size="large" onClick={this.openModal.bind(this)}>Add</Button>
                <Modal centered = {true} style={{height:'650px'}} size="small" open={open}>
                    <Modal.Header>Add Notification</Modal.Header>
                        <Modal.Content scrolling>
                            <Select placeholder="Select a currency" value={this.state.currency}
                                options = {
                                    this.props.currencies.map((t)=>{return {key:t,value:t,text:t}})
                                }
                                onChange={this.onChangeCurrency.bind(this)}
                            />
                            <br/>
                            <Label pointing> Please select a currency</Label>
                            <br/>
                            <Select placeholder="Select a notify Type" value={this.state.notifyType}
                                options = {
                                    [{key:'EMAIL',value:'EMAIL',text:'EMAIL'},{key:'PHONE',value:'PHONE',text:'PHONE'}]
                                }
                                onChange={this.onChangeNotifyType.bind(this)}
                            />
                            <br/>
                            <Label pointing> Please select a notify Type</Label>
                            <br/>
                            <Select placeholder="Select a type" value={this.state.type} onChange={this.onChangeType.bind(this)} options={
                            [
                                {key:'MA',value:'MA',text:'MA'},
                                {key:'DIFFTHRESHOLD',value:'DIFFTHRESHOLD',text:'DIFFTHRESHOLD'}
                            ]}/>
                            <br/>
                            <Label pointing> Please select a type</Label>
                            <br/>
                            {
                                this.state.type == 'MA' ? MACondition:ThreHoldCondition
                            }
                        </Modal.Content>
                        <Modal.Actions>
                            <Button primary onClick={this.onSubmit.bind(this)}>
                                Add
                            </Button>
                            <Button onClick={()=>{this.setState({open:false})}}>
                                Close
                            </Button>
                        </Modal.Actions>
                </Modal>
            </div>
    );
    }
}
AddNotificationModal.defaultProps = {
    open:false,
    currency:'',
    currencies:[],
    notifyType:'EMAIL',
    type:'MA',
    direction:'UP',
    ma_market_place:'COINEX',
    ma_interval:'1HOUR',
    ma_arg_1:5,
    ma_arg_2:10,
    markets:[],
    threDiff:0.05
};
export default AddNotificationModal;
