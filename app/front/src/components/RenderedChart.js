import React, { useEffect, useState, useRef } from "react";
import Chart from "./Chart";

import moment from "moment";

const translate = {
    "daily": "journalier",
    "weekly": "hebdomadaire",
    "monthly": "mensuel"
}

const RenderedChart = (props) => {

    const [data, setData] = useState({})
    const inputRef = useRef(null)

    const _getData = async (date, accuracy) => {
        const dateObject = new Date(date)
        let splitDateString = date.split('-')
        const dateString = splitDateString[0] + '-' + splitDateString[1] + '-' + splitDateString[2] + 'T00:00:00Z'

        let dateAfter
        if (accuracy === 'daily') {
            dateAfter = moment(dateObject).add(1, 'd').format('YYYY-MM-DD')
        } else if (accuracy === 'weekly') {
            dateAfter = moment(dateObject).add(1, 'w').format('YYYY-MM-DD')
        } else {
            dateAfter = moment(dateObject).add(1, 'M').format('YYYY-MM-DD')
        }


        splitDateString = dateAfter.split('-')
        const dateStringAfter = splitDateString[0] + '-' + splitDateString[1] + '-' + splitDateString[2] + 'T00:00:00Z'

        const data = await fetch("http://127.0.0.1:8080/api/v1/uplink/date", {
            method: 'POST',
            body: {
                a: dateString,
                b: dateStringAfter
            }
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
        setData(data_json)
    }

    useEffect(() => {
        const currentDate = moment()
        const stringDate = currentDate.format("YYYY-MM-DD")
        inputRef.current.value = stringDate
        if (Object.keys(data).length === 0) {
            _getData(stringDate, props.accuracy)
        }

        return
    }, [])

    const handleDateChange = async (e) => {
        await _getData(e.target.value, props.accuracy)
    }

    return (
        <div className="mt-8 mb-8 mr-8 flex flex-col justify-center items-center" >
            <p className="text-3xl mb-2 font-sans font-semibold text-blue-600">Détail {translate[props.accuracy]}</p>
            <input ref={inputRef} type="date" onChange={handleDateChange} className="mb-2" />
            {/* <Chart data={data} /> */}
        </div>
    )

}

export default RenderedChart