import React from "react";
import Chart from "./Chart";

import moment from "moment";

const translate = {
    "daily": "journalier",
    "weekly": "hebdomadaire",
    "monthly": "mensuel"
}

class RenderedChart extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            date: "",
            data: {}
        }
    }

    _getData = async (date, accuracy) => {
        const data = await fetch("http://127.0.0.1:8080/api/v1/uplink", {
            method: 'GET',
        })

        const data_json = await data.json()
        data_json.map(elt => {
            let received

            if (accuracy === "daily") {
                received = moment(elt["receivedAt"]).add(1, 'hour').format("hh:mm")
            }
            else {
                received = moment(elt["receivedAt"]).add(1, 'hour').format("DD/MM/YYYY")
            }

            elt["receivedAt"] = received
        })
        this.setState({ data: data_json })
    }

    componentDidMount() {
        const currentDate = new Date()
        const day = currentDate.getDate()
        const stringDate = currentDate.getFullYear() + '-' + (parseInt(currentDate.getMonth()) + 1) + '-' + (parseInt(day) < 10 ? '0' + day : day)
        this.setState({ date: stringDate })
        if (Object.keys(this.state.data).length === 0) {
            this._getData(stringDate, this.props.accuracy)
        }
        return
    }

    handleDateChange = async (e) => {
        this.setState({ date: e.target.value })
        await this._getData(e.target.value, this.props.accuracy)
    }

    render() {
        return (
            <div className="mt-8 mb-8 mr-8 flex flex-col justify-center items-center" >
                <p className="text-3xl mb-2 font-sans font-semibold text-blue-600">Détail {translate[this.props.accuracy]}</p>
                <form>
                    <input type="date" onChange={this.handleDateChange} className="mb-2" value={this.state.date} />
                </form>
                <Chart data={this.state.data} />
            </div>
        )
    }
}

export default RenderedChart