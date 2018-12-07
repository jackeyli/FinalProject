import React,{Component} from 'react';
import {Table,Button,Grid,Label,Input} from 'semantic-ui-react';
import axios from 'axios';
import moment from 'moment';
class NotificationHistoryPage extends Component{
    constructor(props){
        super(props);
        this.state = {
            histories:[],
            dateFrom:moment().startOf('day').format('YYYY/MM/dd'),
            dateTo:moment().endOf('day').format('YYYY/MM/dd')
        }
    }
    onSearchClick(){
        axios.post('request/test/notificationHistory',{
            dateFrom:moment(this.state.dateFrom,'YYYY-MM-DD').startOf('day').format('YYYY-MM-DD HH:mm:ss'),
            dateTo:moment(this.state.dateFrom,'YYYY-MM-DD').endOf('day').format('YYYY-MM-DD HH:mm:ss'),
        }).then((res)=>{
            this.setState({histories:res.data.content});
        });
    }
    render(){
        return (
            <Grid padded>
                <Grid.Row>
                    <Grid.Column>
                        <Label>from </Label>
                        <Input type="date" placevalue={this.state.dateFrom} onChange={(arg1,arg2)=>{this.setState({dateFrom:arg2.value})}}/>
                        <Label>to</Label>
                        <Input type="date" value={this.state.dateTo} onChange={(arg1,arg2)=>{this.setState({dateTo:arg2.value})}}/>
                        <Button primary onClick={()=>this.onSearchClick()}>Search</Button>
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <Grid.Column>
                        <Table>
                            <Table.Header>
                                <Table.Row>
                                <Table.HeaderCell>
                                    Trigger Time
                                </Table.HeaderCell>
                                <Table.HeaderCell>
                                    Type
                                </Table.HeaderCell>
                                <Table.HeaderCell>
                                    Currency Name
                                </Table.HeaderCell>
                                <Table.HeaderCell>
                                    Notify Type
                                </Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>
                            <Table.Body>
                                {
                                    this.state.histories.map((t)=>{
                                        return (
                                            <Table.Row>
                                                <Table.Cell>
                                                    {t['triggerTime']}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    {t['type']}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    {t['currencyName']}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    {t['notifyType']}
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
export default NotificationHistoryPage;