'use strict';

import * as types from './action-types';

export function increment() {
    return {
        type: types.INCREMENT
    };
}

export function decrement() {
    return {
        type: types.DECREMENT
    };
}

export function getNewsSuccess(success,data) {
    return {
        type: types.GETNEWS,
        result:{
            success:success,
            data:data
        }
    };
}

export function getNews() {
    return dispatch =>{
        fetch('https://gitee.com/masonliu/MockData/raw/master/QQTechNews.json')
            .then(response => response.json())
            .then(function(response) {
                dispatch(getNewsSuccess(true,response))
            }).catch(function(err) {
                dispatch(getNewsSuccess(false,null))
            });
    }
}