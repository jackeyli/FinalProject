import React,{Component} from 'react';
import {Table,Grid} from 'semantic-ui-react';
class PriceDiffTable extends Component{
    constructor(props){
        super(props);
    }
    render(){
        debugger;
        return (
            <Grid padded>
                <Grid.Row color="grey">
                    <Grid.Column>
                        {"Price Compare Of " + this.props.curMarket}
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row>
                    <Grid.Column>
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
                                <Table.Cell>{t + '(' + (+this.props.data[t][this.props.curMarket]).toFixed(2) + ')'}</Table.Cell>
                                {
                                    this.props.markets.filter((t)=>t != this.props.curMarket)
                                    .map((p)=>{
                                        const diff = (((+this.props.data[t][this.props.curMarket]) - (+this.props.data[t][p])) /
                                            (+this.props.data[t][this.props.curMarket]) * 100);
                                        return (
                                            <Table.Cell>
                                                <span style = {{color:diff < 0 ? 'green' : 'red'}}>
                                                    {
                                                        this.props.data[t][p].toFixed(2) + '(' + diff.toFixed(2) + '%' + ')'
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
            </Grid.Column>
        </Grid.Row>
        </Grid>
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