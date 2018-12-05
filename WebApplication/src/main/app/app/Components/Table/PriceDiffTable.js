import React,{Component} from 'react';
import {Table} from 'semantic-ui-react';
class PriceDiffTable extends Component{
    constructor(props){
        super(props);
        debugger;
    }
    render(){
        return (
            <div class="panel panel-default" style={{margin:"10px 10px 10px 10px"}}>
                <div class="panel-heading">{"Price Compare Of " + this.props.curMarket}</div>
            <div class="panel-body">
                <Table cell padded>
                    <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>{"Currency(" + this.props.curMarket + ")"}</Table.HeaderCell>
                        {
                            this.props.markets
                            .filter((t)=> t != this.props.curMarket)
                            .map((p)=>{
                                return (
                                    <Table.HeaderCell>
                                    {this.props.curMarket + " - " + p}
                                    </Table.HeaderCell>
                                );
                            })
                        }
                    </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        {this.props.currencies.map((t)=>{
                            return (
                                <Table.Row>
                                <Table.Cell>{this.props.data[t][this.props.curMarket]}</Table.Cell>
                                {
                                    this.props.markets.filter((t)=>t != this.props.curMarket)
                                    .map((p)=>{
                                        const diff = (((+this.props.data[t][this.props.curMarket]) - (+this.props.data[t][p])) /
                                            (+this.props.data[t][this.props.curMarket]) * 100);
                                        return (
                                            <Table.Cell>
                                                <span style = {{color:diff < 0 ? 'green' : 'red'}}>
                                                    {
                                                        this.props.data[t][p] + '(' + diff.toFixed(2) + '%' + ')'
                                                    }
                                                </span>
                                            </Table.Cell>
                                        )
                                    })
                                }
                                </Table.Row>
                            )
                        })}
                    </Table.Body>
                </Table>
            </div>
        </div>
        );
    }
}
PriceDiffTable.defaultProps = {
    data:[],
    curMarket:'',
    markets:[],
    currencies:[]
};
export default PriceDiffTable;