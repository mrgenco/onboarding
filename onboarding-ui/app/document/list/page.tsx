import DocumentCard from './components/DocumentCard'

async function getDocuments() {
    const res = await fetch('http://localhost:8080/document/list', { cache: 'no-store' })
    if (!res.ok) {
        throw new Error('Failed to fetch document information')
    }
    return res.json()
}

interface Document {
    uuid: string;
    title: string;
    lastmodifieddate: string;
    summary: string;
}

export default async function Page() {
    const documents: Document[] = await getDocuments();

    return (
        <div className="grid grid-cols-3 md:grid-cols-2 sm:grid-cols-2 gap-4">
            {documents.map(document =>
                <DocumentCard uuid={document.uuid} title={document.title} summary={document.summary}/>
            )}
        </div>
    )
}