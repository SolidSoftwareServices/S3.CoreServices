using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using NUnit.Framework;

namespace S3.Threading.UnitTests
{
	[TestFixture]
	class ManualResetEventAsyncTests
	{
		[Test]
		public async Task FirstTest()
		{
			var a = new ManualResetEventAsync(false);
			Assert.Pass("TODO");
		}
	}
}
