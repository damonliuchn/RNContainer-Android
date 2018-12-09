'use strict';

import * as types from '../actions/action-types';

const initialState = {
    count: 0,
    news:null
}

export default function main(state = initialState, action){
    switch (action.type){
        case types.INCREMENT:
            return {
                ...state,
                count: state.count + 1
            };
        case types.DECREMENT:
            return {
                ...state,
                count: state.count - 1
            };
        case types.GETNEWS:
            //Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。
            //即合并所有参数
            return Object.assign({}, state, {
                news: action.result
            })
        default:
            return state;
    }
}