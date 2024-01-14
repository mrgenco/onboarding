'use client'

import Link from 'next/link'
import { Button, Card } from 'flowbite-react';

interface DocumentCardProps {
    title: string
    summary: string
    uuid: string
}



export default function DocumentCard(props: DocumentCardProps) {

    return (
        <Card className="max-w-sm">
            <h5 className="text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                {props.title}
            </h5>
            <p className="font-normal text-gray-700 dark:text-gray-400">
                {props.summary}
            </p>
            <p className="text-gray-400">Jonathan Reinink</p>
            <p className="text-gray-400">Aug 18</p>
            <Link className="text-white bg-gradient-to-br from-purple-600 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2" href={`/document/read/${encodeURIComponent(props.uuid)}`}>
                Read more
            </Link>
        </Card>



    )


}