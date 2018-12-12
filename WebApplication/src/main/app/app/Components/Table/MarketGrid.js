import React,{Component} from 'react';
import { Icon, Table } from 'semantic-ui-react'

class MarketGrid extends Component{
    constructor(props) {
        super(props);
    }
    componentDidMount(){
    }
    render(){
        debugger;
        return (
          <Table cell padded>
             <Table.Header>
                <Table.Row>
                    <Table.HeaderCell colSpan={this.props.data.columns.length}>{this.props.data.title}</Table.HeaderCell>
                </Table.Row>
                <Table.Row>
                    {
                        this.props.data.columns.map((p)=>{
                            return (
                                 <Table.HeaderCell>{p}</Table.HeaderCell>
                            );
                        })
                    }
                </Table.Row>
             </Table.Header>
             <Table.Body>
                {
                    this.props.data.datas.map((data)=>{
                        return (
                            <Table.Row>
                                {
                                    this.props.data.columns.map((c)=>{
                                        return (
                                            <Table.Cell>
                                                {
                                                    isNaN(data[c]) ? data[c] :
                                                    (+data[c]).toFixed(2)
                                                }
                                            </Table.Cell>
                                        )
                                    })
                                }
                            </Table.Row>
                        )
                    })
                }
             </Table.Body>
          </Table>
        );
    }
}

MarketGrid.defaultProps = {
    data: {
        title: 'this Table',
        columns: ['name', 'COINEX', 'COIN2', 'POLO', 'd'],
        datas: [{
            key:'BTC',
            name: 'BTC',
            COINEX: 1000,
            COIN2: 1000,
            POLO: 1000,
            d: 1000
        }, {
            key:'BTC',
            name: 'BTC',
            COINEX: 1000,
            COIN2: 1000,
            POLO: 1000,
            d: 1000
        }]
    }
};
export default MarketGrid;