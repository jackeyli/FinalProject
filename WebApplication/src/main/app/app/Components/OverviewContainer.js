import React,{Component} from 'react';
import { Icon, Table,Container,Button } from 'semantic-ui-react'
import MarketGrid from './Table/MarketGrid';
import axios from 'axios';

class OverviewContainer extends Component {
    constructor(props){
        super(props);
        this.state = this.props.data || {
            activeMarkets:[],
            markets:[],
            marketDatas:{
                title: 'this Table',
                columns: [],
                datas: []
            }
        }
    }
    componentDidMount(){
        axios.get('request/test/getCurrencies').then((result)=>{
            const res = result.data,
            markets = res.reduce((pre,cur)=>{
                if(pre.indexOf(cur['marketPlcName']) < 0)
                    pre.push(cur['marketPlcName']);
                return pre;
            },[]).sort((a,b)=>a.localeCompare(b)),
            currencies = res.reduce((pre,cur)=>{
                if(pre.filter((t)=>t['Currency Name'] == cur['name']).length == 0)
                    pre.push({'Currency Name':cur['name'],key:cur['name']});
                return pre;
             },[]),
            datas = currencies.map((m)=>{
               var thisSlot = res.filter((t)=>{return t['name'] == m['Currency Name']});
               thisSlot.forEach((s)=>{
                   m[s['marketPlcName']] = s['price']
               });
               return m;
            }),
            activeMarkets = markets,
            marketDatas = {
                title:'Market Informations',
                columns:['Currency Name'].concat(markets),
                datas: datas
            }
            this.setState({activeMarkets:activeMarkets,markets:markets,marketDatas:marketDatas});
        });
    }
    onMarketClick(newActive){
        var activeButtonsNew = [];
        if(this.state.activeMarkets.filter((t)=>t == newActive).length == 0)
        {
            activeButtonsNew = [newActive].concat(this.state.activeMarkets).sort((a, b) => a.localeCompare(b));
        } else {
            activeButtonsNew = this.state.activeMarkets.filter((t)=>t != newActive);
        }
        this.state.marketDatas.columns = ['Currency Name'].concat(activeButtonsNew);
        this.setState({activeMarkets: activeButtonsNew,
            marketDatas: this.state.marketDatas});
    }
    render(){
        return (
                <Container>
                    <div>
                    {this.state.markets.map((mkt)=>{

                        return this.state.activeMarkets.indexOf(mkt) >= 0 ?
                            (
                            <Button icon labelPosition="left" onClick={()=>this.onMarketClick(mkt)}>
                                <Icon name='check' />
                                {mkt}
                            </Button>
                            ) : (
                            <Button icon labelPosition="left" onClick={()=>this.onMarketClick(mkt)}>
                               {mkt}
                               </Button>
                            )
                    })}
                    </div>
                    <MarketGrid data={this.state.marketDatas}></MarketGrid>
                </Container>
            )
    }

}
export default OverviewContainer