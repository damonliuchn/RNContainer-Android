import React, {Component} from 'react';
import {connect} from 'react-redux';
import * as actions from '../redux/actions/main'
import {
	AppRegistry,
	Image,
	ListView,
	StyleSheet,
	Text,
	View
} from 'react-native';

// 样式
const styles = StyleSheet.create({
	listView: {
		paddingTop: 20,
		backgroundColor: '#ffffff'
	},
	container: {
		flex: 1,
		flexDirection: 'row',
		justifyContent: 'center',
		alignItems: 'center',
		padding: 10,
		backgroundColor: '#ffffff'
	},
	thumbnail: {
		width: 120,
		height: 81
	},
	rightContainer: {
		flex: 1,
		marginLeft:6
	},
	title: {
		fontSize: 16,
		marginBottom: 8
	},
	year: {
	}
});

class MovieList extends Component {

	// 构造函数
	constructor(props) {
		console.log('constructor');
		super(props);
		// 初始化状态
		this.state = {
			dataSource: new ListView.DataSource({
				// 必须，如果两行数据不是同一个数据，返回true
				rowHasChanged: (row1, row2) => {
					return row1 !== row2;
				}
			}),
			loaded: false // 数据加载是否完成
		};
	}

	// 首次渲染之前调用
	componentWillMount() {
		console.log('componentWillMount');
	}

	// 真实DOM渲染之后被调用
	componentDidMount() {
		console.log('componentDidMount');
        this.props.dispatch(actions.getNews())
	}

    componentWillReceiveProps(nextProps){
        if (this.props.state.main.news !== nextProps.state.main.news){
                this.setState({
					dataSource: this.state.dataSource.cloneWithRows(nextProps.state.main.news.data.newslist), // 把数据扔dataSource里
					loaded: true // 加载完成置为true
				});
        }
    }

	// 渲染
	render() {
		console.log('render');
		// 如果数据没有加载完毕
		if (!this.state.loaded) {
			// 渲染加载中
			return this.renderLoadingView();
		}

		// 否则，渲染电影列表
		return <ListView dataSource={this.state.dataSource} renderRow={this.renderMovie} style={styles.listView}/>;
	}
    /**
     * 渲染加载中视图
     * @return {XML}
     */
    renderLoadingView() {
        return (
			<View style={styles.container}>
				<Text>正在加载电影数据</Text>
			</View>
        );
    }

    /**
     * 渲染电影项
     * @param movie
     * @return {XML}
     */
    renderMovie(movie) {
        return (
			<View style={styles.container}>
				<Image style={styles.thumbnail} source={{
                    uri: movie.image
                }}/>
				<View style={styles.rightContainer}>
					<Text style={styles.title}>{movie.title}</Text>
					<Text style={styles.year}>{movie.postdate}</Text>
				</View>
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
        dispatch:dispatch
    };
};
export default connect(mapStateToProps, mapDispatchToProps)(MovieList);
