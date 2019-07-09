import React from 'react';
import './Home.css';


class Home extends React.Component {
    render() {
        return (
         <div>
             <div className="home_container">
                 <p className="title"><span>H</span>ome <span>A</span>utomation</p>
             </div>
             <div className="options">
                 <a href="http://localhost:3000/user" className="button">Register User</a>
                 <a href="http://localhost:3000/register" className="button">Register Device</a>
                 <a href="http://localhost:3000/devices" className="button">Registered Devices</a>
             </div>
        </div>
        );
    }
}

export default Home;