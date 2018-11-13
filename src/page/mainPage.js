'use strict';

import React, {Component} from 'react';
import {Text, View} from 'react-native';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as Actions from '../redux/actions/actionCreator';
import Counter from '../components/counter';

class MainPage extends Component{

    render(){
        const {state, dispatch,bindedActions} = this.props;
        return(
            <View>
                <Counter/>
            </View>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        state: state
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        dispatch:dispatch,
        bindedActions: bindActionCreators(Actions, dispatch)
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(MainPage);