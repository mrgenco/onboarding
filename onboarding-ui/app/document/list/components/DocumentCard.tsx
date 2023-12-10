'use client'

import Link from 'next/link'

interface DocumentCardProps {
    title: string
    summary: string
    uuid: string
}

export default function DocumentCard(props: DocumentCardProps) {

    return (

        <div className="border border-gray-400 bg-white rounded p-4 flex flex-col lg:flex-row justify-between">
            <div className="mb-8">
                <p className="text-sm text-gray-600 flex items-center">
                    Members only
                </p>
                <div className="text-gray-900 font-bold text-xl mb-2">{props.title}</div>
                    <br />
                    <p className="text-gray-700 text-base">{props.summary}</p>
                    <p className="text-gray-900 leading-none">Jonathan Reinink</p>
                    <p className="text-gray-600">Aug 18</p>
                    <Link className="px-6 py-3 text-blue-100 no-underline bg-blue-500 rounded hover:bg-gray-600 hover:text-white-500" href={`/document/read/${encodeURIComponent(props.uuid)}`}>
                        Start Reading
                    </Link>
            </div>
        </div>
    )


}