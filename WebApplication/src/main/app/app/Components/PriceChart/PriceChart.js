import React,{Component} from 'react';
import { Container,Button,Select,Grid} from 'semantic-ui-react'
import axios from 'axios';

class PriceChart extends Component{
    constructor(props){
        super(props);
        this.state = this.props;
    }
    componentDidMount(){
        axios.get('request/test/getCurrencies').then((res)=>{
            const currencies = res.data.content.reduce((pre,cur)=>{
                if(pre.filter((t)=>t['Currency Name'] == cur['name']).length == 0)
                    pre.push({'Currency Name':cur['name'],key:cur['name']});
                return pre;
            },[]);
            axios.post('request/test/currencyPriceHistory',{
                market:this.state.market,
                name:this.state.currency,
                interval:'30MIN'
            }).then((res)=>{
                this.setState({currencies:currencies.map((t)=>t['key']),data:res.data.content,activeInterval:'30MIN'});
            });
        })
    }
    componentDidUpdate() {
        if(!this.chartInstance)
            this.chartInstance = echarts.init(this.chart);
        var splitData = function(rawData) {
            var categoryData = [];
            var values = []
            for (var i = 0; i < rawData.length; i++) {
                categoryData.push(rawData[i].splice(0, 1)[0]);
                values.push(rawData[i])
            }
            return {
                categoryData: categoryData,
                values: values
            };
        }
        var data = splitData(this.state.data.sort((a,b) => {
            return a['time'].localeCompare(b['time']);
        }).map((t)=>{return [t['time'],t['open'],
                t['close'],t['lowest'],
                t['highest']]})
        );
        var calculateMA = function(dayCount,data) {
            var result = [];
            for (var i = 0, len = data.values.length; i < len; i++) {
                if (i < dayCount) {
                    result.push('-');
                    continue;
                }
                var sum = 0;
                for (var j = 0; j < dayCount; j++) {
                    sum += data.values[i - j][1];
                }
                result.push(sum / dayCount);
            }
            return result;
        }
        var option = {
            title: {
                text: this.state.market + ":  " + this.state.currency ,
                left: 0
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            legend: {
                data: ['K Line', 'MA5', 'MA10', 'MA20', 'MA30']
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '15%'
            },
            xAxis: {
                type: 'category',
                data: data.categoryData,
                scale: true,
                boundaryGap : true,
                axisLine: {onZero: false},
                splitLine: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax'
            },
            yAxis: {
                scale: true,
                splitArea: {
                    show: true
                }
            },
            dataZoom: [
                {
                    type: 'inside',
                    start: 50,
                    end: 100
                },
                {
                    show: true,
                    type: 'slider',
                    y: '90%',
                    start: 50,
                    end: 100
                }
            ],
            series: [
                {
                    name: 'K Line',
                    type: 'candlestick',
                    data: data.values,
                    itemStyle: {
                        normal: {
                            color: '#ec0000',
                            color0: '#8A0000',
                            borderColor: '#00da3c',
                            borderColor0: '#008F28'
                        }
                    },
                },
                {
                    name: 'MA5',
                    type: 'line',
                    data: calculateMA(5,data),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: 'MA10',
                    type: 'line',
                    data: calculateMA(10,data),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: 'MA20',
                    type: 'line',
                    data: calculateMA(20,data),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: 'MA30',
                    type: 'line',
                    data: calculateMA(30,data),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                }
            ]
        };
        this.chartInstance.setOption(option);
    }
    onChangeCurrency(arg1,arg2){
        var state = this.state;
        axios.post('request/test/currencyPriceHistory',{
            market:state.market,
            name:arg2.value,
            interval:state.interval
        }).then((res)=>{
            this.setState({data:res.data.content,currency:arg2.value});
        });
    }
    onChangePeriod(period){
        var state = this.state;
        axios.post('request/test/currencyPriceHistory',{
            market:state.market,
            name:state.currency,
            interval:period
        }).then((res)=>{
            this.setState({data:res.data.content,activeInterval:period});
        });
    }
    render(){
        return (
            <Grid padded>
            <Grid.Row color="grey">
                <Grid.Column >
                    {"Price History Of " + this.props.market}
                </Grid.Column>
            </Grid.Row>
            <Grid.Row>
                <Grid.Column>
            <Container>
                <p>
                <Select placeholder="Select a currency" value={this.state.currency}
                    options={this.state.currencies.map((t)=>{
                        return {
                            key:t,
                            value:t,
                            text:t
                        }
                    })}
                    onChange={this.onChangeCurrency.bind(this)}/>
                </p>
                <p>
                    <Button active={this.state.activeInterval == '15MIN'} onClick={()=>this.onChangePeriod('15MIN')}>15 Min</Button>
                    <Button active={this.state.activeInterval == '30MIN'} onClick={()=>this.onChangePeriod('30MIN')}>30 Min</Button>
                    <Button active={this.state.activeInterval == '1HOUR'} onClick={()=>this.onChangePeriod('1HOUR')}>Hour</Button>
                    <Button active={this.state.activeInterval == '4HOUR'} onClick={()=>this.onChangePeriod('4HOUR')}>4 Hour</Button>
                    <Button active={this.state.activeInterval == 'DAY'} onClick={()=>this.onChangePeriod('DAY')}>DAY</Button>
                </p>
                <div style={{width:'100%',height:'300px'}} ref={ (chart) => this.chart = chart }></div>
                </Container>
                </Grid.Column>
            </Grid.Row>
            </Grid>
    )
    }
}
PriceChart.defaultProps = {
    currencies:[],
    currency:'BTC',
    interval:'30MIN',
    data:[],
    market:'COINEX'
};
export default PriceChart;