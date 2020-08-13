import React from 'react';
import axios from 'axios';

class Device extends React.Component {

    constructor(props) {
        super(props);
        this.state = {sensors: <tr></tr>, final:'', val:''};
        this.handleRefresh = this.handleRefresh.bind(this);
        this.handleConfigure = this.handleConfigure.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleRefresh(event){
        event.preventDefault();
        let sensorData = await axios.get("http://localhost:8080/api/sensors");
        let sensorValues = [];
        for(let val of sensorData.data){
            sensorValues.push(
                <tr key={val}>
                    <td>
                        <a href={`api/sensors/${val.alias}`} className="button">{val.alias}</a>
                    </td>
                    <td>
                        <button id={`${val.alias}`} className="button" onClick={this.handleConfigure}>{`Configure ${val.alias}`}</button>
                    </td>
                </tr>)
        }
        this.setState({sensors:sensorValues, final:'', val:''});
    }

    async handleSubmit(event, alias){
        event.preventDefault();
        let s = this.state.val;
        await axios.put("http://localhost:8080/api/sensors/"+alias+"/config", JSON.parse(s));
        this.setState({sensors:<tr></tr>, final:'', val:''});
    }

    async handleChange(event){
        this.state.val = event.target.value;
        this.setState({[event.target.name]:event.target.value});
    }

    async handleConfigure(event){
        event.preventDefault();
        let alias = event.target.id;
        let sensorConfig = await axios.get(`http://localhost:8080/api/sensors/${alias}/config`);
        let val = JSON.stringify(sensorConfig.data);
        this.setState({sensors:<tr></tr>, final:<form onSubmit={(e)=>{this.handleSubmit(e, alias)}}>
                <textarea id="config" onChange={this.handleChange} defaultValue={val}/>
                <input type="submit" value="Submit"/>
        </form>});
    }

    render(){
        return(
            <div>
                <table>
                    <tbody>{this.state.sensors}</tbody>
                </table>
                <p>{this.state.final}</p>
                <input className="button" id="refresh" type="button" value="Refresh" onClick={this.handleRefresh}/>
            </div>
        );
    }
}
export default Device;