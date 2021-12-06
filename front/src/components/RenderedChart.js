import React from "react";
import Chart from "./Chart";

class RenderedChart extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            date: ""
        }
    }

    componentDidMount() {
        const currentDate = new Date()
        const day = currentDate.getDate()
        const stringDate = currentDate.getFullYear() + '-' + (parseInt(currentDate.getMonth()) + 1) + '-' + (parseInt(day) < 10 ? '0' + day : day)
        this.setState({ date: stringDate })
        return
    }

    handleDateChange = (e) => {
        this.setState({ date: e.target.value })
    }

    render() {
        return (
            <div className="mt-8 mb-8 mr-8 flex flex-col justify-center items-center">
                <p className="text-3xl mb-2 font-sans font-semibold text-blue-600">Détail journalier</p>
                <form>
                    <input type="date" onChange={this.handleDateChange} className="mb-2" value={this.state.date} />
                </form>
                <Chart date={this.state.date} />
            </div>
        )
    }
}

export default RenderedChart