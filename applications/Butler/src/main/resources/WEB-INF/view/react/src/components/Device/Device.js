import React from 'react';
import axios from 'axios';

class Device extends React.Component {

    constructor(props) {
        super(props);
        this.state = {sensors: <tr></tr>};
        this.handleRefresh = this.handleRefresh.bind(this);
    }

    async handleRefresh(event){
        event.preventDefault();
        let sensorData = await axios.get("api/sensors");
        let sensorValues = [];
        for(let val of sensorData.data){
            sensorValues.push(
                <tr key={val}>
                    <td>
                        <a href={`api/sensors/${val.alias}`} className="button">{val.alias}</a>
                    </td>
                </tr>)
        }
        this.setState({sensors:sensorValues});
    }

    render(){
        return(
            <div>
                <table>
                    <tbody>{this.state.sensors}</tbody>
                </table>
                <input className="button" id="refresh" type="button" value="Refresh" onClick={this.handleRefresh}/>
            </div>
        );
    }
}
export default Device;