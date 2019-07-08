import React from 'react';
import axios from 'axios';
import './User.css';

class User extends React.Component{

    constructor(props) {
        super(props);
        this.url = props.url;
        this.state = {name:'', email:'', password:'', success:''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleSubmit(event){
        event.preventDefault();
        await axios.post(this.url + "/save", this.state);
        this.setState({name:'', email:'', password:'', success:'Successfully submitted'});
    }


    handleChange(event){
        this.setState({[event.target.name]:event.target.value});
    }

    render(){
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit}>
                    <div className="row">
                        <div className="col-25">
                            <label for="name"> Name </label>
                        </div>
                        <div className="col-75">
                            <input id="name" name = "name" type="text" placeholder="Your name.." value={this.state.name} onChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-25">
                            <label for="email"> Email </label>
                        </div>
                        <div className="col-75">
                            <input id="email" name = "email" type="text" placeholder="Your email.." value={this.state.email} onChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-25">
                            <label for="password"> Email Password </label>
                        </div>
                        <div className="col-75">
                            <input id="password" name="password" type="password" placeholder="Your email password.." value={this.state.password} onChange={this.handleChange}/>
                        </div>
                    </div>
                    <input type="submit" value="Submit"/>
                </form>
                <br/>
                <div>{this.state.success}</div>
            </div>
        );
    }

}

export default User;