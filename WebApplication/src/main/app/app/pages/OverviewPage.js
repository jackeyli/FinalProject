import React,{Component} from 'react';
import {Container} from 'semantic-ui-react'
import OverviewContainer from '../Components/OverviewContainer';
import PriceChart from '../Components/PriceChart/PriceChart';
class OverviewPage extends Component{
    render(){
        return (
            <div>
                <OverviewContainer/>
                <PriceChart market={"BCEX"}/>
                <PriceChart market={"COINEX"}/>
                <PriceChart market={"HUOBI"}/>
                <PriceChart market={"POLONIEX"}/>
            </div>
    );
    }
}
export default OverviewPage;