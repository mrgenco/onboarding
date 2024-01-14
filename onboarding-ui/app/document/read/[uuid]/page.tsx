import ReactMarkdown from 'react-markdown'
import { Card } from 'flowbite-react';

async function renderDocument(uuid : string) {
    const res = await fetch('http://localhost:8080/document/raw/' + uuid, { cache: 'no-store' })
    console.log(uuid);
    if (!res.ok) {
        throw new Error('Failed to fetch document information')
    }
    return res.json()
}
interface DocumentResponse {
    content: string;
    title: string;
    lastUpdatedTime : string;
}

export default async function Page({ params }: { params: { uuid: string} }) {
    
    const document: DocumentResponse = await renderDocument(params.uuid);


    return (

        <Card>
            <h5 className="text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                {document.title}
            </h5>
            <h2 className="text-2xl font-bold mb-8 text-gray-900 dark:text-white">About this document</h2>
            <h3 className="text-gray-900 dark:text-white">Last updated : {document.lastUpdatedTime} </h3>
            <h3 className="text-gray-900 dark:text-white">Written by : </h3>
            <p className="text-gray-400">Jonathan Reinink</p>
            <p className="text-gray-400">Aug 18</p>
            <div className="relative w-full px-6 py-12 bg-white shadow-xl shadow-slate-700/10 ring-1 ring-gray-900/5 md:max-w-3xl md:mx-auto lg:max-w-4xl lg:pt-16 lg:pb-28">
                <article className="prose lg:prose-xl">
                    <ReactMarkdown>
                        {document.content}
                    </ReactMarkdown>
                </article>
            </div>
        </Card>

    )
}