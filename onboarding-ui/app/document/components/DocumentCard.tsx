import Link from 'next/link'

interface DocumentCardProps {
    title: string
    summary: string
    uuid: string
}

export default function DocumentCard(props: DocumentCardProps){

    return (

        <div className="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 flex flex-col justify-between leading-normal">
            <div className="mb-8">
                <p className="text-sm text-gray-600 flex items-center">
                    Members only
                </p>
                <div className="text-gray-900 font-bold text-xl mb-2">{props.title}</div>
                <p className="text-gray-700 text-base">{props.summary}</p>
            </div>
            <div className="flex items-center">
                <div className="text-sm">
                    <p className="text-gray-900 leading-none">Jonathan Reinink</p>
                    <p className="text-gray-600">Aug 18</p>
                    <br />
                    <div className="flex flex-row-reverse">
                        <Link className="px-6 py-3 text-blue-100 no-underline bg-blue-500 rounded hover:bg-blue-600 hover:underline hover:text-blue-200" href={`/document/read/${encodeURIComponent(props.uuid)}`}>
                            Start Reading
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    )


}