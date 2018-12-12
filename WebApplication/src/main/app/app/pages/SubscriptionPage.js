import React,{Component} from 'react';
import AddNotificationModal from '../Components/AddNotificationModal';
import { Icon, Table,Grid,Button} from 'semantic-ui-react'
import axios from 'axios';
import Bus from '../Components/MessageBus/Bus';
class SubscriptionPage extends Component{
    constructor(props){
        super(props);
        this.state = {currencies:[],markets:[],notifications:[]};
        this.refModal = React.createRef();
    }
    createDeleteFunc(arg){
        const thisNotification = arg;
        return ()=>{
            axios.get('request/test/delNotiCondition?id=' + thisNotification['oid'])
                .then((res)=>{
                Bus.dispatch({
                    type:'subscriptionChange'
                });
            })
        }
    }
    onButtonClick(){
        axios.post("request/test/storeNotiCondition",requestObject).then((res)=>{
            Bus.dispatch({
                type:'subscriptionChange'
            });
        });
    }
    componentDidMount(){
        Bus.subscribe(()=>{
            let state = Bus.getState();
            if(state.type == 'subscriptionChange'){
                axios.get('request/test/notiCondition').then((res)=>{
                    this.setState({notifications:res.data.content});
                });
            }
        });
        axios.get('request/test/getCurrencies').then((res)=>{
            const currencies = res.data.content.reduce((pre,cur)=>{
                if(pre.indexOf(cur['name']) < 0)
                    pre.push(cur['name']);
                return pre;
            },[]);
            const markets = res.data.content.reduce((pre,cur)=>{
                if(pre.indexOf(cur['marketPlcName']) < 0)
                    pre.push(cur['marketPlcName']);
                return pre;
            },[]);
            axios.get('request/test/notiCondition').then((res)=>{
                this.setState({
                currencies:currencies,
                markets:markets,
                notifications:res.data.content});
            });
        });
    }
    render(){
        return (
            <Grid padded>
            <Grid.Row>
                <Grid.Column>
                    <AddNotificationModal
                        currencies={this.state.currencies}
                        markets={this.state.markets}
                    />
                </Grid.Column>
            </Grid.Row>
            <Grid.Row>
                <Grid.Column>
            <Table cell padded>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>
                            Condition Type
                        </Table.HeaderCell>
                        <Table.HeaderCell>
                            Currency Name
                        </Table.HeaderCell>
                        <Table.HeaderCell>
                            Method
                        </Table.HeaderCell>
                        <Table.HeaderCell>
                            Action
                        </Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                {
                    this.state.notifications.map((t)=>{
                        return (
                            <Table.Row>
                                <Table.Cell>
                                    {t['type']}
                                </Table.Cell>
                                <Table.Cell>
                                    {t['name']}
                                </Table.Cell>
                                <Table.Cell>
                                    {t['notifyType']}
                                </Table.Cell>
                                <Table.Cell>
                                    <Button icon="close" onClick={this.createDeleteFunc(t).bind(this)}/>
                                </Table.Cell>
                            </Table.Row>
                        )
                    })
                }
                </Table.Body>
            </Table>
            </Grid.Column>
            </Grid.Row>
        </Grid>
        );
    }
}
export default SubscriptionPage;