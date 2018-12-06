import React,{Component} from 'react';
import {Router,Route,IndexRoute,browserHistory} from "react-router";
import OverviewPage from './pages/OverviewPage';
import PriceDiffPage from './pages/PriceDiffPage';
import SubscriptionPage from './pages/SubscriptionPage';
import {Home} from './Home';
class App extends Component{
    render(){
        return (
            <div>
                <Router history={browserHistory}>
                    <Route path={"/"} component={Home}>
                        <IndexRoute component={OverviewPage}/>
                        <Route path={"overview"} component={OverviewPage} />
                        <Route path={"pricediff"} component={PriceDiffPage} />
                        <Route path={"subscription"} component={SubscriptionPage} />
                    </Route>
                </Router>
            </div>
        );
    }
}
export default App;