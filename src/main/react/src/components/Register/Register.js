import React from 'react';
import axios from 'axios';
import './Register.css';

class Register extends React.Component {

    constructor(props){
        super();
        this.url = props.url;
        this.state = {sensors:<tr></tr>, final:[]};
        this.handleRefresh = this.handleRefresh.bind(this);
        this.handleChangeSelect = this.handleChangeSelect.bind(this);
        this.submitValues = this.submitValues.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleRefresh(event){
        event.preventDefault();
        let sensorData = await axios.get(this.url + "sensors/unmapped");
        let types = await axios.get(this.url + "/sensors/types");
        let options = types.data.map((obj,index) =>(<option value={obj} key={index}>{obj}</option>));
        let sensorValues = [];
        for(let val of sensorData.data){
            let id = val.id.id;
            let mac = val.id.address.mac;
            sensorValues.push(
                <tr key={mac}>
                    <td>{id}</td>
                    <td>{mac}</td>
                    <td>
                        <select onChange={(e) => this.handleChangeSelect(e, val)} name="types">{options}
                        </select>
                    </td>
                    <td>
                        <input type="text" id="alias" name="alias" onChange={(e) => this.handleChange(e, val)}/>
                    </td>
                </tr>)
        }
        this.setState({sensors:sensorValues});
    }

    handleChange(event, val){
        this.setState({[event.target.name]:event.target.value});
        val.alias = event.target.value;
    }

    handleChangeSelect(e, val){
        val.type = e.target.value;
        if(!this.state.final.includes(val))
            this.state.final.push(val);
    }

    async submitValues(){
           await axios.put(this.url + '/sensors/', this.state.final);
           this.setState({sensors:<tr></tr>});
    }

    render(){
     return(
         <div>
             <div>
                 <table id="results">
                     <tbody>
                     <tr>
                         <th>Sensor ID</th>
                         <th>MAC Address</th>
                         <th>Sensor Type</th>
                         <th>Alias</th>
                     </tr>
                     {this.state.sensors}
                     </tbody>
                 </table>
             </div>
             <input className="button" id="refresh" type="button" value="Refresh" onClick={this.handleRefresh}/>
             <input className="button" id="submit" type="button" value="Submit" onClick={this.submitValues}/>
         </div>
     );
    }
}

export default Register;