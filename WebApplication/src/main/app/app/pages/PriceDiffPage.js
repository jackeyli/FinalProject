import React,{Component} from 'react';
import PriceDiffTable from '../Components/Table/PriceDiffTable';
import {Container} from 'semantic-ui-react';
import axios from 'axios';
import _ from "lodash";
class PriceDiffPage extends Component{
    constructor(props){
        super(props);
        this.state = {data:[]};
    }
    componentDidMount(){
        axios.get('request/test/getCurrencies').then((result)=>{
            const res = result.data;
            const currencies = res
                .reduce((pre,cur)=>{
                if(!pre[cur['name']])
                    pre[cur['name']] = {};
                return pre;
            },{});
            this.setState({data:res,currencies:_.keys(currencies)});
        });
    }
    render(){
        return (
            <Container>
              <PriceDiffTable data={this.state.data
                    .reduce((pre,cur)=>{
                        if(!pre[cur['name']])
                            pre[cur['name']] = {};
                        pre[cur['name']][cur['marketPlcName']] = cur['price'];
                        return pre;
                    },{})}
                  curMarket = "COINBASE"
                  markets={["COINBASE","COINEX","HUOBI","POLONIEX"]}
                  currencies={this.state.currencies}
                  />
              <PriceDiffTable data={this.state.data
                            .reduce((pre,cur)=>{
                            if(!pre[cur['name']])
                                pre[cur['name']] = {};
                            pre[cur['name']][cur['marketPlcName']] = cur['price'];
                            return pre;
                            },{})}
                  curMarket="COINEX"
                  markets={["COINBASE","COINEX","HUOBI","POLONIEX"]}
                  currencies={this.state.currencies}
                  />
              <PriceDiffTable data={this.state.data
                            .reduce((pre,cur)=>{
                            if(!pre[cur['name']])
                                pre[cur['name']] = {};
                            pre[cur['name']][cur['marketPlcName']] = cur['price'];
                            return pre;
                            },{})}
                  curMarket="HUOBI"
                  markets={["COINBASE","COINEX","HUOBI","POLONIEX"]}
                  currencies={this.state.currencies}/>
              <PriceDiffTable data={this.state.data
                            .reduce((pre,cur)=>{
                            if(!pre[cur['name']])
                                pre[cur['name']] = {};
                            pre[cur['name']][cur['marketPlcName']] = cur['price'];
                            return pre;
                            },{})}
                  curMarket="POLONIEX"
                  markets={["COINBASE","COINEX","HUOBI","POLONIEX"]}
                  currencies={this.state.currencies}/>
            </Container>
        );
    }
}
export default PriceDiffPage;