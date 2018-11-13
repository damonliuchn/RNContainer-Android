'use strict';

import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {Text, View, Button} from 'react-native';
import * as actions from '../redux/actions'

class Counter extends Component{
    static contextTypes = {
        store: PropTypes.object.isRequired
    };
    static childContextTypes = {//http://react-china.org/t/react-redux-redux-store/11242
        store: PropTypes.object.isRequired    // childContextTypes必须声明  这一句很重要
    }
    getChildContext = () => {
        return {
            store: this.context.store
        }
    }
    // componentWillMount() {
    //     // 监听 store 变化
    //     store.subscribe(this.watchStore.bind(this));
    // }
    //
    // componentWillUnmount() {
    //     // 对 store 变化取消监听
    //     store.unsubscribe(this.watchStore.bind(this));
    // }
    //
    // // 监听回调函数，当 store 变化后执行
    // watchStore(){
    //     // 回调函数中重新设置状态
    //     this.setState(this.getCurrentState());
    // }

    handleClick = ()=>{
        //this.props.dispatch(actions.decrement())
        this.props.dispatch(actions.getNews())
        //this.context.store.dispatch(actions.decrement())
    }
    render(){
        const {text} = this.props;
        return(
            <View>
                <Text style={{fontSize: 25, alignSelf: 'center'}}>{this.props.state.counter.count}</Text>
                <Button title="add122ddddd333sdfsd订单呃呃呃" onPress={this.handleClick}/>
                <Text> </Text>
                <Button title="subtract" onPress={this.handleClick}/>
            </View>
        );
    }
    componentWillReceiveProps(nextProps){
        if (this.props.state.counter.news !== nextProps.state.counter.news){
           alert('变化'+ nextProps.state.counter.news.data.newslist.length)
        }
    }
}

const mapStateToProps = (state) => {
    return {
        state: state
    };
};
const mapDispatchToProps = (dispatch) => {
    return {
        dispatch:dispatch
    };
};
export default connect(mapStateToProps, mapDispatchToProps)(Counter);