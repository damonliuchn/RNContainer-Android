'use strict';

import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Text, View, Button} from 'react-native';

export default class Counter extends Component{
    //设置控件的属性
    static propTypes = {
        onPress: PropTypes.object
    };

    render(){
        const {text, onPress} = this.props;
        return(
            <View>
                <Text style={{fontSize: 25, alignSelf: 'center'}}>{text}</Text>
                <Button title="add122ddddd333sdfsd订单" onPress={onPress.increment}/>
                <Text> </Text>
                <Button title="subtract" onPress={onPress.decrement}/>
            </View>
        );
    }
}